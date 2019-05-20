package heyu.guarded;

import lombok.*;

/**
 * @author heyu
 * @date 2019/5/20
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private long id;

    private String data;
}
