package com.example.domain.intractor


abstract class UseCaseWithParams<in Params, out R> {
    /**
     * Build the use case to be executed.
     *
     * @param params required to build this use case.
     *
     * @return result [R] of the use case.
     */
    protected abstract suspend fun buildUseCase(params: Params) : R

    /**
     * Execute the use case.
     *
     * Called by client of the use case.
     *
     * @return [R] result of executing this use case
     */
    suspend fun execute(params: Params): R = buildUseCase(params)





}

