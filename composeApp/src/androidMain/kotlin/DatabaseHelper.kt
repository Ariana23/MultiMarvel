package ui

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.multimarvel.MarvelDatabase
import utils.data.initDatabase


fun initializeDatabase(context: Context) {
    val driver = AndroidSqliteDriver(MarvelDatabase.Schema, context, "marvel.db")
    initDatabase(driver)
}
