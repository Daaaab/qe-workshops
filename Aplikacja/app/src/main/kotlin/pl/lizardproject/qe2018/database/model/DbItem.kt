package pl.lizardproject.qe2018.database.model

import io.requery.*
import pl.lizardproject.qe2018.model.Category
import pl.lizardproject.qe2018.model.Priority

@Entity
interface DbItem : Persistable {
    @get:Key @get:Generated var id: Int

    var name: String
    var isChecked: Boolean
    var category: Category
    var priority: Priority

    @get:ManyToOne(cascade = arrayOf(CascadeAction.NONE)) var user: DbUser
}