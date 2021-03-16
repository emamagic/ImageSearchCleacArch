package com.example.domain.intractor

abstract class UseCaseWithParamsImmediate<in Params, out R> {

    protected abstract fun buildUseCaseImmediate(params: Params) : R

    fun executeImmediate(params: Params): R = buildUseCaseImmediate(params)
}