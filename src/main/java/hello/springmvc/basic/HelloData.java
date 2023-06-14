package hello.springmvc.basic;

import lombok.Data;

/**
 * 롬복 @Data
 * @Getter, @Setter, @ToString,
 * @EqualAndHashCode, @RequiredArgsConstructor 등을
 * 자동으로 적용해준다.
 */
@Data
public class HelloData {
    private String username;
    private int age;
}
