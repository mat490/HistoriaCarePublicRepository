package mx.sgahc.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class CredentialsDTO {
    private String sub;
    private String aud;
    private Long iat;
    private Long exp;
}
