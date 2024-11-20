import React, { createContext, useContext, useState, useCallback } from 'react';
import apiService from '../services/api';

interface AuthContextType {
  user: any | null;
  login: (telephone: string, motDePasse: string) => Promise<void>;
  logout: () => void;
  isAuthenticated: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<any | null>(null);

  const login = useCallback(async (telephone: string, motDePasse: string) => {
    try {
      const response = await apiService.login({ telephone, motDePasse });
      setUser(response.data);
    } catch (error) {
      throw new Error('Échec de la connexion');
    }
  }, []);

  const logout = useCallback(() => {
    setUser(null);
  }, []);

  return (
    <AuthContext.Provider value={{ user, login, logout, isAuthenticated: !!user }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};