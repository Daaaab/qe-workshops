package com.futureprocessing.qe.workshops.databinding

import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.google.android.material.textfield.TextInputLayout

@BindingMethods(value = [BindingMethod(type = TextInputLayout::class, attribute = "app:errorText", method = "setError")])
class TextInputLayoutBindingAdapters