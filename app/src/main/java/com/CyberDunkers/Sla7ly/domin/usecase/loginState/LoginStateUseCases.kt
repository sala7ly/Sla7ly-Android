package com.CyberDunkers.Sla7ly.domin.usecase.loginState

data class LoginStateUseCases(
    val getLoginState: GetLoginState,
    val saveLoginState: SaveLoginState,
    val logoutUseCase: LogoutUseCase
)
