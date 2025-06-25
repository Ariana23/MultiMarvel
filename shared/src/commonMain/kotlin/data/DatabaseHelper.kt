package utils.data

import app.cash.sqldelight.db.SqlDriver
import org.multimarvel.MarvelDatabase

lateinit var database: MarvelDatabase

fun initDatabase(driver: SqlDriver) {
    database = MarvelDatabase(driver)
}
