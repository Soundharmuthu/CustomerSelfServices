import { BrowserRouter, NavLink, Route, Routes } from 'react-router-dom';
import AccountStatement from './Components/AccountStatement';
import Footer from "./Components/Footer";
import LoginForm from './Components/loginform';// Import your Loginpage component
import CustomerServiceMenu from "./Components/CustomerServiceMenu"; // Import your CustomerServiceMenu component
import MyAccount from "./Components/MyAccount"; // Import your MyAccount component
import ServiceRequestMenu from "./Components/ServiceRequestMenu"; // Import your ServiceRequestMenu component
import ChequeBookRequest from './Components/ChequeBookRequest';
import StolenDebitCard from './Components/StolenDebitCard';
import CreditCardRequest from './Components/CreditCardRequest';
import Header from './Components/Header';
import React from 'react';
import RequestStatus from './Components/viewrequest';
import './App.css';


import Profile from './Components/profile';

function App() {
  return (
   
//     const express = require('express');
// const cors = require('cors');
// const app = express();
// app.use(cors());
    <BrowserRouter>
    <div>
      <Header />
       <Routes>
          <Route exact path='/' element={<LoginForm />} />
          <Route exact path='/CustomerServiceMenu' element={<CustomerServiceMenu />} />
          <Route exact path='/MyAccount' element={<MyAccount />} />
          <Route exact path="//AccountStatement/:accountNumber" element={<AccountStatement />}/>
          <Route exact path='/ServiceRequestMenu' element={<ServiceRequestMenu />} />
          <Route exact path='/AccountStatement' element={<AccountStatement />} />
          {/* <Route exact path='/MyAccount' element={<MyAccount />} /> */}
          <Route exact path='/ChequeBookRequest' element={<ChequeBookRequest />} />
          {/* <Route exact path='/employee/edit/:id' element={<AddEmployee />} /> */}
          <Route exact path='/StolenDebitCard' element={<StolenDebitCard />} />
          <Route exact path='/CreditCardRequest' element={<CreditCardRequest />} />
          <Route exact path='/profile' element={<Profile />}></Route>
          <Route exact path='/view' element={<RequestStatus/>}></Route>
          
       
        </Routes>
        
        <Footer />
        </div>
    </BrowserRouter>
    
    
  );
}

export default App;

