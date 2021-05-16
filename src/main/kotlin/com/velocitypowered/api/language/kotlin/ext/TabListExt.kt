package com.velocitypowered.api.language.kotlin.ext

import com.velocitypowered.api.proxy.player.TabList
import com.velocitypowered.api.proxy.player.TabListEntry
import java.util.*

val TabList.entries: Collection<TabListEntry>
    inline get() = entries()

operator fun TabList.contains(uniqueId: UUID): Boolean =
    containsEntry(uniqueId)

operator fun TabList.plus(entry: TabListEntry): TabList = apply {
    addEntry(entry)
}
