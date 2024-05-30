package dev.subrotokumar.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetailDto {
    private String recipient;
    private String msgBody;
    private String subject;
}