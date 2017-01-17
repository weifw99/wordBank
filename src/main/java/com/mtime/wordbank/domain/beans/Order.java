package com.mtime.wordbank.domain.beans;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Mtime on 2016/7/21.
 */
@Getter
@Setter
@XmlRootElement
public class Order implements Serializable{
    private Integer id;
    private String name;
    private Double price;
}
