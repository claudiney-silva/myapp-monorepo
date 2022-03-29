package com.github.claudineysilva.monorepo.libs.contracts.foo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FooDto {
    private String id;
    private String fooAttribute;
}
