/*
 * Copyright 2016 Victor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.victoralbertos.breadcumbs_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
final class DotView extends RelativeLayout {
  private final View dotViewVisitedStep;

  DotView(Context context, boolean visited, int visitedStepBorderDotColor,
      int visitedStepFillDotColor, int nextStepBorderDotColor,
      int nextStepFillDotColor, int radius, int sizeBorderLine, int step) {
    super(context);

    setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

    addView(createDot(nextStepBorderDotColor, nextStepFillDotColor, radius, sizeBorderLine, 0));

    dotViewVisitedStep =
        createDot(visitedStepBorderDotColor, visitedStepFillDotColor, radius, sizeBorderLine, step);

    if (!visited) {
      dotViewVisitedStep.setScaleX(0);
      dotViewVisitedStep.setScaleY(0);
    }

    addView(dotViewVisitedStep);

    //For testing purpose
    setTag("dotView");
    dotViewVisitedStep.setTag("dotViewVisitedStep");
  }

  private View createDot(int borderColor, int fillColor, int radius, int sizeBorderLine, int step) {
//    View dotView = new View(getContext());
//    dotView.setLayoutParams(new LinearLayout.LayoutParams(radius * 2, radius * 2));
//
//    GradientDrawable border = new GradientDrawable();
//    border.setShape(GradientDrawable.OVAL);
//    border.setColor(fillColor);
//    border.setStroke(sizeBorderLine, borderColor);
//
//    dotView.setBackground(border);
    View dotView =  LayoutInflater.from(getContext()).inflate(R.layout.img_layout, null);
    TextView textView = (TextView) dotView.findViewById(R.id.tv_num);
    textView.setText(step+"");
    return dotView;
  }

  void animateFromNextStepToVisitedStep(Runnable endAnim) {
    dotViewVisitedStep
        .animate()
        .scaleX(1)
        .scaleY(1)
        .withEndAction(endAnim);
  }

  void animateFromVisitedStepToNextStep(Runnable endAnim) {
    dotViewVisitedStep
        .animate()
        .scaleX(0)
        .scaleY(0)
        .withEndAction(endAnim);
  }
}

