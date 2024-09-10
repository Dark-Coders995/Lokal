package com.works.lokal.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

sealed class NavItem {
    object Home :
        Item(path = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)

    object BookMark :
        Item(
            path = NavPath.BOOKMARK.toString(),
            title = NavTitle.BOOKMARK,
            icon = Icons.Default.Favorite
        )

}