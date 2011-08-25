package com.rent.exchange;

import android.view.animation.Interpolator;

public class ElasticInterpolator implements Interpolator {

	private float amplitude;
	private float period;
	private EasingType.Type type;

	public ElasticInterpolator(EasingType.Type paramType, float paramFloat1,
			float paramFloat2) {
		this.type = paramType;
		this.amplitude = paramFloat1;
		this.period = paramFloat2;
	}

	private float in(float paramFloat1, float paramFloat2, float paramFloat3) {
		float f1 = 0;
		if (paramFloat1 == 0.0F) {
			f1 = 0.0F;
		} else {

			if (paramFloat1 < 1.0F) {
			}
			f1 = 1.0F;
		}
		if (paramFloat3 == 0.0F)
			paramFloat3 = 0.3F;
		float f2 = 0;
		if ((paramFloat2 == 0.0F) || (paramFloat2 < 1.0F)) {
			paramFloat2 = 1.0F;
			f2 = paramFloat3 / 4.0F;
		}
		double d1 = paramFloat2;
		float f3 = paramFloat1 - 1.0F;
		double d2 = 10.0F * f3;
		double d3 = Math.pow(2.0D, d2);
		double d4 = d1 * d3;
		double d5 = (f3 - f2) * 6.283185307179586D;
		double d6 = paramFloat3;
		double d7 = Math.sin(d5 / d6);
		f1 = (float) (-(d4 * d7));
		double d8 = paramFloat3 / 6.283185307179586D;
		double d9 = Math.asin(1.0F / paramFloat2);
		f2 = (float) (d8 * d9);
		return f1;
	}

	private float inout(float paramFloat1, float paramFloat2, float paramFloat3) {
		float f1 = 0;
		if (paramFloat1 == 0.0F)
			f1 = 0.0F;
		else {
			if (paramFloat1 >= 1.0F) {
				f1 = 1.0F;
			} else if (paramFloat3 == 0.0F)
				paramFloat3 = 0.45F;
			float f2 = 0;
			if ((paramFloat2 == 0.0F) || (paramFloat2 < 1.0F)) {
				paramFloat2 = 1.0F;
				f2 = paramFloat3 / 4.0F;
			}
			while (true) {
				paramFloat1 *= 2.0F;
				if (paramFloat1 >= 1.0F) {
				} else {
					double d1 = paramFloat2;
					float f3 = paramFloat1 - 1.0F;
					double d2 = 10.0F * f3;
					double d3 = Math.pow(2.0D, d2);
					double d4 = d1 * d3;
					double d5 = (f3 - f2) * 6.283185307179586D;
					double d6 = paramFloat3;
					double d7 = Math.sin(d5 / d6);
					double d8 = d4 * d7;
					f1 = (float) (-0.5D * d8);
					double d9 = paramFloat3 / 6.283185307179586D;
					double d10 = Math.asin(1.0F / paramFloat2);
					f2 = (float) (d9 * d10);
				}
				double d11 = paramFloat2;
				float f4 = paramFloat1 - 1.0F;
				double d12 = -10.0F * f4;
				double d13 = Math.pow(2.0D, d12);
				double d14 = d11 * d13;
				double d15 = (f4 - f2) * 6.283185307179586D;
				double d16 = paramFloat3;
				double d17 = Math.sin(d15 / d16);
				f1 = (float) (d14 * d17 * 0.5D + 1.0D);
			}
		}
		return f1;
	}

	private float out(float paramFloat1, float paramFloat2, float paramFloat3) {
		float f1 = 0;
		if (paramFloat1 == 0.0F)
			f1 = 0.0F;
		else {
			if (paramFloat1 < 1.0F) {
			}
			f1 = 1.0F;
		}
		if (paramFloat3 == 0.0F)
			paramFloat3 = 0.3F;
		float f2 = 0;
		if ((paramFloat2 == 0.0F) || (paramFloat2 < 1.0F)) {
			paramFloat2 = 1.0F;
			f2 = paramFloat3 / 4.0F;
		}
		double d1 = paramFloat2;
		double d2 = -10.0F * paramFloat1;
		double d3 = Math.pow(2.0D, d2);
		double d4 = d1 * d3;
		double d5 = (paramFloat1 - f2) * 6.283185307179586D;
		double d6 = paramFloat3;
		double d7 = Math.sin(d5 / d6);
		f1 = (float) (d4 * d7 + 1.0D);
		double d8 = paramFloat3 / 6.283185307179586D;
		double d9 = Math.asin(1.0F / paramFloat2);
		f2 = (float) (d8 * d9);
		return f1;
	}

	public float getInterpolation(float paramFloat) {
		EasingType.Type localType1 = this.type;
		EasingType.Type localType2 = EasingType.Type.IN;
		float f3 = 0;
		if (localType1 == localType2) {
			float f1 = this.amplitude;
			float f2 = this.period;
			f3 = in(paramFloat, f1, f2);
		} else {
			EasingType.Type localType3 = this.type;
			EasingType.Type localType4 = EasingType.Type.OUT;
			if (localType3 == localType4) {
				float f4 = this.amplitude;
				float f5 = this.period;
				f3 = out(paramFloat, f4, f5);
			} else {
				EasingType.Type localType5 = this.type;
				EasingType.Type localType6 = EasingType.Type.INOUT;
				if (localType5 == localType6) {
					float f6 = this.amplitude;
					float f7 = this.period;
					f3 = inout(paramFloat, f6, f7);
				}
				int i = 0;
			}
		}
		return f3;
	}
}
