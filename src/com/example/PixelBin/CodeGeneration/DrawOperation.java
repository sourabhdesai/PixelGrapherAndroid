/* 
 * Copyright 2012 Google Inc. All Rights Reserved.
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

package com.example.PixelBin.CodeGeneration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public enum DrawOperation {
    SET_PAINT() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // red, green, blue, alpha
            paint.setARGB(args[3], args[0], args[1], args[2]);
        }
    },
    LINE() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // x1, y1, x2, y2
            canvas.drawLine(args[0], args[1], args[2], args[3], paint);
        }
    },
    FILL_RECT() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // x1, y1, x2, y2
            paint.setStyle(Style.FILL);
            canvas.drawRect(args[0], args[1], args[2], args[3], paint);
        }
    },
    FRAME_RECT() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // x1, y1, x2, y2
            paint.setStyle(Style.STROKE);
            canvas.drawRect(args[0], args[1], args[2], args[3], paint);
        }
    },
    FILL_CIRCLE() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // cx, cy, radius
            paint.setStyle(Style.FILL);
            canvas.drawCircle(args[0], args[1], args[2], paint);
        }
    },
    FRAME_CIRCLE() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // cx, cy, radius
            paint.setARGB(255, args[0], args[1], args[2]);
            paint.setStyle(Style.STROKE);
            canvas.drawCircle(args[0], args[1], args[2], paint);
        }
    },
    DRAW_NUMBER() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // x, y, value
            // TODO: font/size support
            canvas.drawText(String.valueOf(args[2]), args[0], args[1], paint);
        }
    },
    DRAW_CHAR() {
        public void execute(Canvas canvas, Paint paint, Integer[] args) {
            // x, y, value
            canvas.drawText(String.valueOf((char)args[2].intValue()), args[0], args[1], paint);
        }
    },
    ;
    
    public abstract void execute(Canvas canvas, Paint paint, Integer[] args);

}
