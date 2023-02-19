import React, { useEffect, useState, useContext } from "react";
import {Routes, Route, Navigate, useNavigate } from "react-router-dom";
import {responseMsg} from "./ResponseMsg"
import {CommonContext} from "../../App";

function Header() {
    const navigate = useNavigate();
    const myPages = useContext(CommonContext).myPages;

    const childMenuInit = (ele) => {
        if (!ele) return;

        return (
                <div
                    style={{padding:"6px",paddingLeft:`${ele.depth*10}px`}}
                    key={ele.url}
                ><span onClick={() => navigate(ele.url)}>{ele.name}</span>

                    {ele.children.map(ele => childMenuInit(ele))}
                </div>
        )
    }

    return (
        <div style={{display:"flex"}}>
            {myPages.map(ele => {
                if (ele.depth === 1) {

                    return (childMenuInit(ele));
                }
            })}
            
            <div
                style={{padding:"6px"}}
                onClick={() => {
                    fetch('/api/logout')
                      .then((res) => res.json())
                      .then((json) => {
                          if(json.code === responseMsg.SUCCESS.code) window.location.reload();
                      })
                }}>로그아웃
            </div>
        </div>
    );
}

export default Header;