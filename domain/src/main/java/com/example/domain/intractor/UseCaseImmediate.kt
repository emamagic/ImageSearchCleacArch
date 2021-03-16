package com.example.domain.intractor

abstract class UseCaseImmediate<out R> {

    protected abstract fun buildUseCaseImmediate() : R

    fun executeImmediate(): R = buildUseCaseImmediate()
}