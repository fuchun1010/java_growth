package com.tank.clazz;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.annimon.stream.IntStream;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Supplier;
import io.vavr.Function1;
import lombok.NonNull;
import lombok.val;
import org.junit.Test;

import java.util.List;

public class TimeMomentTest {

  @Test
  public void momentTest() {
    val moments = this.momentKey(() -> DateUtil.format(DateUtil.date(), "yyyyMMdd"))
            .apply("szpszx");
    Stream.of(moments).forEach(System.out::println);
  }

  @Test
  public void hourTest() {
    val result = DateUtil.format(DateUtil.date(), "HH");
    System.out.println(result);
  }

  @Test
  public void currentMomentKeyTest() {
    val orgCode = "szpszx";
    val result = this.currentMomentKey(orgCode);
    System.out.println(result);
  }


  private String currentMomentKey(@NonNull final String orgCode) {
    val date = DateUtil.date();
    val hour = DateUtil.format(date, "HH");
    val dateStr = DateUtil.format(date, "yyyyMMdd");
    return Optional.ofNullable(hour)
            .map(rs -> rs.length() == 1 ? StrUtil.format("{}{}", "0", hour) : hour)
            .map(rs -> StrUtil.format("{}:{}{}", orgCode, dateStr, rs))
            .orElse("-");
  }

  private Function1<String, List<String>> momentKey(@NonNull final Supplier<String> supplier) {
    return (orgCode) -> IntStream.rangeClosed(0, 23)
            .boxed()
            .map(index -> index < 10 ? StrUtil.format("{}{}", "0", index) : String.valueOf(index))
            .map(index -> StrUtil.format("{}{}", supplier.get(), index))
            .map(tail -> StrUtil.format("{}:{}", orgCode, tail))
            .toList();
  }

}
