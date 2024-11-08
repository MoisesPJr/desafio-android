package com.picpay.desafio.android.feature.userList.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.feature.userList.data.api.PicPayService
import com.picpay.desafio.android.feature.userList.data.db.PicPayDb
import com.picpay.desafio.android.feature.userList.data.db.UserDao
import com.picpay.desafio.android.feature.userList.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.feature.userList.data.source.UserDataSourceImpl
import com.picpay.desafio.android.feature.userList.domain.repository.UserRepository
import com.picpay.desafio.android.feature.userList.domain.source.UserDataSource
import com.picpay.desafio.android.feature.userList.domain.useCase.GetUsersUseCase
import com.picpay.desafio.android.feature.userList.domain.useCase.GetUsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserModule {


    @Provides
    @Singleton
    fun providesUserDataSource(api: PicPayService, userDao: UserDao): UserDataSource {
        return UserDataSourceImpl(api, userDao)
    }


    @Provides
    @Singleton
    fun providesUserRepository(dataSource: UserDataSource): UserRepository {
        return UserRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun providesUserUseCase(repository: UserRepository): GetUsersUseCase {
        return GetUsersUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PicPayDb::class.java,
        "PicPayDB"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideYourDao(db: PicPayDb) = db.getDao()
}