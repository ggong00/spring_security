import React, { useEffect, useState, createContext } from "react";
import {Routes, Route, Navigate, useNavigate } from "react-router-dom";
import {responseMsg} from "./components/common/ResponseMsg"
import Login from "./components/login/Login";
import Signup from "./components/signup/Signup";
import Error_404 from "./components/common/Error_404";
import AuthRoute from "./components/common/AuthRoute"
import './App.css';

export const CommonContext = createContext();

function App() {
    const [myPages, setMyPages] = useState([]);
    const navigate = useNavigate();

    const menuInit = (arr,menus) => {
        if (!menus) return;

        menus.forEach(ele => {
            arr.push(ele);
            menuInit(arr,ele.children);
        });
    }

    useEffect(() => {
        fetch("/api/system/menus/my")
            .then((res) => res.json())
            .then((json) => {
                if(json.code === responseMsg.UN_AUTHORIZED.code) navigate("/login");
                else {
                    const menus = [];
                    menuInit(menus,json.data);
                    setMyPages(menus);
                }
            });
    },[navigate]);

    return (
    <div className="App">
        <CommonContext.Provider value={{myPages:myPages}}>
            <Routes>
                <Route path="/" element={<Navigate to="/main" replace />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />

                {myPages.map((menu) => {
                    return (
                        <Route
                            key={menu.url}
                            path={menu.url}
                            element={<AuthRoute to={menu.url} name={menu.name} />}
                        />
                    );
                })}

                <Route path='*' element={<Error_404 />}/>
            </Routes>
        </CommonContext.Provider>
    </div>
    );
}

export default App;
