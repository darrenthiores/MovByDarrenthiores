package com.darrenthiores.ticket.di

import com.darrenthiores.movbybwa.history.HistoryViewModel
import com.darrenthiores.movbybwa.ticket.TicketViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ticketModule = module {
    viewModel { TicketViewModel() }
    viewModel { HistoryViewModel() }
}