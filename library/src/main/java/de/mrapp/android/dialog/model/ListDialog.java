/*
 * Copyright 2014 - 2020 Michael Rapp
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
package de.mrapp.android.dialog.model;

/**
 * Defines the interface, a dialog, which is designed according to Android 5's Material design
 * guidelines even on pre-Lollipop devices and may contain list items, must implement.
 *
 * @author Michael Rapp
 * @since 3.2.0
 */
public interface ListDialog extends ButtonBarDialog, ListDialogDecorator {

    /**
     * Defines the interface, a class that should be notified when a list item of a {@link
     * ListDialog} has been selected or unselected, must implemented.
     */
    interface OnItemSelectedListener {

        /**
         * The method, which is invoked when a list item has been selected or unselected.
         *
         * @param position
         *         The position of the list item, which has been selected, as an {@link Integer}
         *         value
         * @param selected
         *         True, if the item has been selected, false otherwise
         */
        void onItemSelectionStateChanged(int position, boolean selected);

    }

    /**
     * Defines the interface, a class that should be notified when a list item of a
     * {@link ListDialog} has been enabled or disabled, must implement.
     */
    interface OnItemEnabledListener {

        /**
         * The method, which is invoked when a list item has been enabled or disabled.
         *
         * @param position
         *         The position of the list item, which has been enabled, as an {@link Integer}
         *         value
         * @param enabled
         *         True, if the item has been enabled, false otherwise
         */
        void onItemEnableStateChanged(int position, boolean enabled);

    }

}