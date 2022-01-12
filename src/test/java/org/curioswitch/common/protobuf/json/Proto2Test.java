/*
 * Copyright (c) 2022-2022 Choko (choko@curioswitch.org)
 * SPDX-License-Identifier: MIT
 */

package org.curioswitch.common.protobuf.json;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.JsonFormat.Parser;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.curioswitch.common.protobuf.json.test.Proto2Test.Foo;
import org.curioswitch.common.protobuf.json.test.Proto2Test.Proto2Response;
import org.junit.jupiter.api.Test;

class Proto2Test {

  private static final Parser UPSTREAM_PARSER = JsonFormat.parser();

  private static final byte[] SEARCH_RESPONSE_JSON;
  private static final Proto2Response PROTO2_RESPONSE;

  static {
    try {
      SEARCH_RESPONSE_JSON = "{\"message\": \"hello\", \"foo\": 1}".getBytes(StandardCharsets.UTF_8);

      Proto2Response.Builder builder = Proto2Response.newBuilder();
      UPSTREAM_PARSER.merge(new String(SEARCH_RESPONSE_JSON, StandardCharsets.UTF_8), builder);
      PROTO2_RESPONSE = builder.build();
    } catch (IOException e) {
      throw new Error(e);
    }
  }

  @Test
  void parse() {
    assertThat(PROTO2_RESPONSE).isNotNull();
    assertThat(PROTO2_RESPONSE.getFoo()).isEqualTo(Foo.A);
  }
}
