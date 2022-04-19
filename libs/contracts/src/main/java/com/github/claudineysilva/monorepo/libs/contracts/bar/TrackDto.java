package com.github.claudineysilva.monorepo.libs.contracts.bar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {
    private String id;
    private String barAttribute;
    private String bazAttribute;
    private String fooAttribute;
}
