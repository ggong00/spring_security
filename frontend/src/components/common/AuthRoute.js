import React, { useEffect, useState, useContext } from "react";
import {Routes, Route, Navigate, useNavigate } from "react-router-dom";
import Header from "./Header";
import Main from "../main/Main";
import System from "../system/System"
import Auth from "../system/Auth"

function AuthRoute({to,name}) {

    return (
        <>
            <Header />
            { to === '/main' &&  <Main name={name}/> }
            { to === '/system' && <System name={name}/> }
            { to === '/system/auth' && <Auth name={name}/> }
        </>
    );
}

export default AuthRoute;