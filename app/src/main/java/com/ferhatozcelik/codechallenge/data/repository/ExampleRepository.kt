package com.ferhatozcelik.codechallenge.data.repository

import com.ferhatozcelik.codechallenge.data.dao.ExampleDao
import com.ferhatozcelik.codechallenge.data.remote.AppApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExampleRepository @Inject constructor(private val appApi: AppApi, private val exampleDao: ExampleDao)