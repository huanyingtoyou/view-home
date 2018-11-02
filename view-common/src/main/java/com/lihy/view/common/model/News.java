package com.lihy.view.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lihy
 * @date 2018/10/31
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private String title;
    private String content;
    private String url;
    private Date postdate;
}
