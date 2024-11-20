import axios from 'axios';

const API_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export interface RegisterData {
  nom: string;
  prenom: string;
  telephone: string;
  deviceMonnaie: string;
  motDePasse: string;
  typeCompte: 'e' | 'c';
  decouvert?: number;
  taux?: number;
}

export interface LoginData {
  telephone: string;
  motDePasse: string;
}

export interface TransferData {
  tel1: string;
  tel2: string;
  motDePasse1: string;
  montant: number;
}

export interface WithdrawData {
  telephone: string;
  motDePasse: string;
  montant: number;
}

const apiService = {
  register: (data: RegisterData) => 
    api.post('/createCompte', data),
  
  login: (data: LoginData) => 
    api.post('/login', data),
  
  transfer: (data: TransferData) => 
    api.post('/transferer', data),
  
  withdraw: (data: WithdrawData) => 
    api.post('/retrait', data),
};

export default apiService;