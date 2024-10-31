import { Route, Routes } from "react-router-dom";
import Login from "../components/Login";

function App() {
  return (
    <div className="App">
      <Login/>
        <Routes>
          <Route path="/login" element={<Login/>}/>
        </Routes>
    </div>
  );
}

export default App;
