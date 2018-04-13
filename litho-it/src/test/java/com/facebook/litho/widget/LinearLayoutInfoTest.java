/*
 * Copyright 2014-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.litho.widget;

import static android.support.v7.widget.OrientationHelper.HORIZONTAL;
import static android.support.v7.widget.OrientationHelper.VERTICAL;
import static com.facebook.litho.SizeSpec.EXACTLY;
import static com.facebook.litho.SizeSpec.UNSPECIFIED;
import static com.facebook.litho.SizeSpec.makeSizeSpec;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.robolectric.RuntimeEnvironment.application;

import android.support.v7.widget.LinearLayoutManager;
import com.facebook.litho.testing.testrunner.ComponentsTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for {@link LinearLayoutInfo}
 */
@RunWith(ComponentsTestRunner.class)
public class LinearLayoutInfoTest {

  @Test
  public void testOrientations() {
    final LinearLayoutInfo verticalLinearLayoutInfo = new LinearLayoutInfo(
        application,
        VERTICAL,
        false);

    assertThat(VERTICAL).isEqualTo(verticalLinearLayoutInfo.getScrollDirection());

    final LinearLayoutInfo horizontalLinearLayoutInfo = new LinearLayoutInfo(
        application,
        HORIZONTAL,
        false);

    assertThat(HORIZONTAL).isEqualTo(horizontalLinearLayoutInfo.getScrollDirection());
  }

  @Test
  public void testGetLayoutManager() {
    final LinearLayoutInfo linearLayoutInfo = new LinearLayoutInfo(
        application,
        VERTICAL,
        false);

    assertThat(linearLayoutInfo.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
  }

  @Test
  public void testApproximateRangeVertical() {
    final LinearLayoutInfo linearLayoutInfo = new LinearLayoutInfo(
        application,
        VERTICAL,
        false);

    int rangeSize = linearLayoutInfo.approximateRangeSize(10, 10, 10, 100);

    assertThat(rangeSize).isEqualTo(10);
  }

  @Test
  public void testApproximateRangeHorizontal() {
    final LinearLayoutInfo linearLayoutInfo = new LinearLayoutInfo(
        application,
        HORIZONTAL,
        false);

    int rangeSize = linearLayoutInfo.approximateRangeSize(10, 10, 100, 10);

    assertThat(rangeSize).isEqualTo(10);
  }

  @Test
  public void testGetChildMeasureSpecVertical() {
    final LinearLayoutInfo linearLayoutInfo = new LinearLayoutInfo(
        application,
        VERTICAL,
        false);
    final int sizeSpec = makeSizeSpec(200, EXACTLY);

    final int heightSpec = linearLayoutInfo.getChildHeightSpec(sizeSpec, null);
    assertThat(makeSizeSpec(0, UNSPECIFIED)).isEqualTo(heightSpec);

    final int widthSpec = linearLayoutInfo.getChildWidthSpec(sizeSpec, null);
    assertThat(sizeSpec).isEqualTo(widthSpec);
  }

  @Test
  public void testGetChildMeasureSpecHorizontal() {
    final LinearLayoutInfo linearLayoutInfo = new LinearLayoutInfo(
        application,
        HORIZONTAL,
        false);
    final int sizeSpec = makeSizeSpec(200, EXACTLY);

    final int heightSpec = linearLayoutInfo.getChildHeightSpec(sizeSpec, null);
    assertThat(sizeSpec).isEqualTo(heightSpec);

    final int widthSpec = linearLayoutInfo.getChildWidthSpec(sizeSpec, null);
    assertThat(makeSizeSpec(0, UNSPECIFIED)).isEqualTo(widthSpec);
  }
}
