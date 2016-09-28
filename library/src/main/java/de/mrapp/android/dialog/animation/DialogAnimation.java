/*
 * Copyright 2014 - 2016 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mrapp.android.dialog.animation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import static de.mrapp.android.util.Condition.ensureAtLeast;
import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An abstract base class for all animation, which can be used to show or hide a dialog.
 *
 * @author Michael Rapp
 * @since 3.7.0
 */
public abstract class DialogAnimation {

    /**
     * An abstract base class for all builders, which allow to create animations, which can be used
     * to show or hide a dialog.
     *
     * @param <AnimationType>
     *         The type of the animations, which are created by the builder
     * @param <BuilderType>
     *         The type of the builder
     */
    protected static abstract class AbstractAnimationBuilder<AnimationType extends DialogAnimation, BuilderType extends AbstractAnimationBuilder> {

        /**
         * Animation, which is configured by the builder.
         */
        private AnimationType animation;

        /**
         * Returns the builder itself, casted to the generic type BuilderType.
         *
         * @return The builder itself as an instance of the generic type BuilderType
         */
        @SuppressWarnings("unchecked")
        protected BuilderType self() {
            return (BuilderType) this;
        }

        /**
         * The method, which is invoked on subclasses in order to create the animation, which is
         * configured by the builder.
         *
         * @param context
         *         The context, which should be used by the animation, as an instance of the class
         *         {@link Context}. The context may not be null
         * @return The animation, which has been created, as an instance of the generic type
         * AnimationType. The animation may not be null
         */
        @NonNull
        protected abstract AnimationType createAnimation(@NonNull final Context context);

        /**
         * Creates a new builder, which allows to create animations, which can be used to show or
         * hide a dialog.
         *
         * @param context
         *         The context, which should be used by the builder, as an instance of the class
         *         {@link Context}. The context may not be null
         */
        protected AbstractAnimationBuilder(@NonNull final Context context) {
            ensureNotNull(context, "The context may not be null");
            animation = createAnimation(context);
        }

        /**
         * Sets the interpolator, which should be used by the animation, which is created by the
         * builder.
         *
         * @param interpolator
         *         The interpolator, which should be set, as an instance of the type {@link
         *         Interpolator}. The interpolator may not be null
         * @return The builder, this method has been called upon, as an instance of the generic type
         * BuilderType
         */
        public BuilderType setInterpolator(@NonNull final Interpolator interpolator) {
            animation.setInterpolator(interpolator);
            return self();
        }

        /**
         * Sets the duration of the animation, which is created by the builder.
         *
         * @param duration
         *         The duration, which should be set, in milliseconds as a {@link Long} value. The
         *         duration must be at least 1
         * @return The builder, this method has been called upon, as an instance of the generic type
         * BuilderType
         */
        public BuilderType setDuration(final long duration) {
            animation.setDuration(duration);
            return self();
        }

        /**
         * Creates the animation, which has been configured by the builder.
         *
         * @return The animation, which has been configured by the builder, as an instance of the
         * generic type AnimationType. The animation may not be null
         */
        @NonNull
        public final AnimationType create() {
            return animation;
        }

    }

    /**
     * The interpolator, which is used by the animation.
     */
    private Interpolator interpolator;

    /**
     * The duration of the animation in milliseconds.
     */
    private long duration;

    /**
     * Sets the interpolator, which should be used by the animation.
     *
     * @param interpolator
     *         The interpolator, which should be set, as an instance of the type {@link
     *         Interpolator}. The interpolator may not be null
     */
    protected final void setInterpolator(@NonNull final Interpolator interpolator) {
        ensureNotNull(interpolator, "The interpolator may not be null");
        this.interpolator = interpolator;
    }

    /**
     * Sets the duration of the animation.
     *
     * @param duration
     *         The duration, which should be set, in milliseconds as a {@link Long} value. The
     *         duration must be at least 1
     */
    protected final void setDuration(final long duration) {
        ensureAtLeast(duration, 1, "The duration must be at least 1");
        this.duration = duration;
    }

    /**
     * Creates a new animation, which can be used to show or hide a dialog.
     *
     * @param context
     *         The context, which should be used by the animation, as an instance of the class
     *         {@link Context}. The context may not be null
     */
    protected DialogAnimation(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        interpolator = new AccelerateDecelerateInterpolator();
        duration = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    /**
     * Returns the interpolator, which is used by the animation.
     *
     * @return The interpolator, which is used by the animation, as an instance of the type {@link
     * Interpolator}. The interpolator may not be null
     */
    @NonNull
    public final Interpolator getInterpolator() {
        return interpolator;
    }

    /**
     * Returns the duration of the animation.
     *
     * @return The duration of the animation in milliseconds as a {@link Long} value
     */
    public final long getDuration() {
        return duration;
    }

}