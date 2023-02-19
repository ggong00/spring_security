import React, { useEffect, useState, useContext } from "react";
import {Routes, Route, Navigate, useNavigate } from "react-router-dom";

function System({name}) {

    return (
        <div>
            {name}
        </div>
    );
}

export default System;