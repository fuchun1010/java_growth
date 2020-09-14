package com.tank.common.entity;

import com.google.common.base.MoreObjects;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Comparator;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false, of = {"code"})
public class Organization implements Comparator<Organization> {

  @Override
  public int compare(Organization first, Organization second) {
    return first.getCode().compareTo(second.getCode());
  }

  private String desc;

  private String code;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("code", code).add("desc", desc).toString();
  }
}
