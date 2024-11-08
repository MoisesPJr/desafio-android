package com.picpay.desafio.android.feature.userList.domain.useCase

import com.picpay.desafio.android.feature.userList.domain.mapper.toDomain
import com.picpay.desafio.android.feature.userList.domain.model.UserDomain
import com.picpay.desafio.android.feature.userList.domain.repository.UserRepository
import javax.inject.Inject


interface GetUsersUseCase{
    suspend operator fun invoke(): List<UserDomain>
}

class GetUsersUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): GetUsersUseCase {
    override suspend fun invoke(): List<UserDomain> {
        return repository.getUsers().map { it.toDomain() }
    }


}