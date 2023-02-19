import React, { useEffect, useState, useContext } from "react";
import {Routes, Route, Navigate, useNavigate } from "react-router-dom";
import {responseMsg} from "../common/ResponseMsg";
import { useForm } from "react-hook-form";

function Login() {
    const { register, handleSubmit, formState: { isSubmitting, isDirty, errors }} = useForm();
    const navigate = useNavigate();

    const onSubmit = (formData) => {
        fetch("/api/login", {
            method : 'post',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
            .then((res) => res.json())
            .then((json) => {
                if (json.code === responseMsg.LOGIN_FAIL.code) {
                    alert('로그인 실패');
                } else if(json.code === responseMsg.LOGIN_SUCCESS.code) {
                    navigate('/main');
                }
            });
    }

    return (
        <div>
            <form className="" onSubmit={handleSubmit(onSubmit)} >
                <header>로그인</header>
                <div className="">
                    <div className="">
                        <label>아이디</label>
                        <input
                            className=""
                            type="text"
                            name="userId"
                            {...register("userId")}
                        />
                    </div>
                    <div className="input_group">
                        <label>비밀번호</label>
                        <input
                            className=""
                            type="password"
                            name="userPass"
                            {...register("userPass")}
                        />
                    </div>
                    <div className="">
                        <button className="" type="submit">로그인</button>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Login;