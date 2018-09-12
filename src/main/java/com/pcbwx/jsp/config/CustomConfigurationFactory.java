package com.pcbwx.jsp.config;

import com.pcbwx.jsp.common.ConfigProperties;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;

import java.net.URI;

/**
 * @author heyu
 */
@Plugin(name = "CustomConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(Ordered.HIGHEST_PRECEDENCE)
@org.springframework.context.annotation.Configuration
public class CustomConfigurationFactory extends ConfigurationFactory{

	private Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Level.INFO);
        if (ConfigProperties.getDebugLog()) {
            builder.add(getConsoleBuilder(builder));
            addDevLogger(builder);
            builder.add(builder.newRootLogger(Level.INFO).add(builder.newAppenderRef("Console")));
        } else {
            builder.add(getErrorFileBuilder(builder));
            builder.add(getInfoFileBuilder(builder));
            builder.add(getAsyncBuilder(builder));
            builder.add(builder.newRootLogger(Level.INFO).add(builder.newAppenderRef("ASYNC")));
        }
        return builder.build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
        return getConfiguration(loggerContext, source.toString(), null);
    }

    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final String name, final URI configLocation) {
        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
        return createConfiguration(name, builder);
    }

    @Override
    protected String[] getSupportedTypes() {
        return new String[] {"*"};
    }

	/**
	 * 获取显示台适配器
	 * @param builder
	 * @return
	 */
	private AppenderComponentBuilder getConsoleBuilder(
			ConfigurationBuilder<BuiltConfiguration> builder) {
		AppenderComponentBuilder appenderBuilder = builder.newAppender(
				"Console", "Console").addAttribute("target",
				ConsoleAppender.Target.SYSTEM_OUT);
		appenderBuilder.add(builder.newLayout("PatternLayout").addAttribute(
				"pattern",
				"%d{HH:mm:ss} %-5level %logger{36} [%L] - %msg%n"));
		return appenderBuilder;
	}

	/**
	 * 获取Error文件日志适配器
	 * @param builder
	 * @return
	 */
	private AppenderComponentBuilder getErrorFileBuilder(
			ConfigurationBuilder<BuiltConfiguration> builder) {
		AppenderComponentBuilder appenderBuilder = builder
				.newAppender("myInfo", "RollingFile")
				.addAttribute("fileName", "/opt/logs/jsp/info/info.log")
				.addAttribute("filePattern", "/opt/logs/jsp/info/info-%d{yyyy-MM-dd}.log");
		appenderBuilder.add(builder.newLayout("ThresholdFilter")
				.addAttribute("level", "INFO")
				.addAttribute("onMatch", "ACCEPT")
				.addAttribute("onMismatch", "DENY"));
		appenderBuilder.add(builder.newLayout("PatternLayout").addAttribute(
				"pattern",
				"%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} [%L] - %msg%n"));
		appenderBuilder.add(builder.newLayout("Policies").addComponent(
				builder.newLayout("TimeBasedTriggeringPolicy")
						.addAttribute("modulate", true)
						.addAttribute("interval", "1")));
		return appenderBuilder;
	}

	/**
	 * 获取Info文件日志适配器
	 * @param builder
	 * @return
	 */
	private AppenderComponentBuilder getInfoFileBuilder(
			ConfigurationBuilder<BuiltConfiguration> builder) {
		AppenderComponentBuilder appenderBuilder = builder
				.newAppender("myError", "RollingFile")
				.addAttribute("fileName",
						"/opt/logs/jsp/error/error.log")
				.addAttribute("filePattern",
						"/opt/logs/jsp/error/error-%d{yyyy-MM-dd}.log");
		appenderBuilder.add(builder.newLayout("ThresholdFilter")
				.addAttribute("level", "ERROR")
				.addAttribute("onMatch", "ACCEPT")
				.addAttribute("onMismatch", "DENY"));
		appenderBuilder.add(builder.newLayout("PatternLayout").addAttribute(
				"pattern",
				"%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} [%L] - %msg%n"));
		appenderBuilder.add(builder.newLayout("Policies").addComponent(
				builder.newLayout("TimeBasedTriggeringPolicy")
						.addAttribute("modulate", true)
						.addAttribute("interval", "1")));
		return appenderBuilder;
	}

	/**
	 * 获取异步写日志适配器
	 * @param builder
	 * @return
	 */
	private AppenderComponentBuilder getAsyncBuilder(
			ConfigurationBuilder<BuiltConfiguration> builder) {
		AppenderComponentBuilder appenderBuilder = builder
				.newAppender("ASYNC", "Async");
		appenderBuilder.add(builder.newLayout("AppenderRef")
					.addAttribute("ref", "myInfo"));
		appenderBuilder.add(builder.newLayout("AppenderRef")
					.addAttribute("ref", "myError"));
		return appenderBuilder;
	}

	/**
	 * 添加开发环境的支持
	 * @param builder
	 */
	private void addDevLogger(ConfigurationBuilder<BuiltConfiguration> builder){
		// 添加mybatis支持
		LoggerComponentBuilder loggerMyBatisBuilder = builder
				.newLogger("com.pcbwx.jsp.dao", "debug")
				.addAttribute("additivity", false)
				.add(builder.newAppenderRef("Console"));
		builder.add(loggerMyBatisBuilder);
		
		LoggerComponentBuilder loggerSpringBuilder = builder
				.newLogger("org.springframework", "INFO")
				.addAttribute("additivity", false)
				.add(builder.newAppenderRef("Console"));
		builder.add(loggerSpringBuilder);
	}
}
