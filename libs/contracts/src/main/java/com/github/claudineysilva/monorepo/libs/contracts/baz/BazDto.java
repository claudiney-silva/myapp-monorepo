package com.github.claudineysilva.monorepo.libs.contracts.baz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BazDto {
    private String id;
    private String bazAttribute;
}
