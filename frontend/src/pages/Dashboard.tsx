import React from 'react';
import { Link } from 'react-router-dom';
import { ArrowUpRight, ArrowDownRight, Clock, CreditCard } from 'lucide-react';

const Dashboard = () => {
  // Mock data - In a real app, this would come from your API
  const accountBalance = 5000.75;
  const recentTransactions = [
    { id: 1, type: 'credit', amount: 1000, description: 'Dépôt', date: '2024-03-15' },
    { id: 2, type: 'debit', amount: 50.25, description: 'Retrait ATM', date: '2024-03-14' },
    { id: 3, type: 'debit', amount: 120, description: 'Virement à Jean', date: '2024-03-13' },
  ];

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <h1 className="text-2xl font-bold text-gray-900 mb-8">Tableau de bord</h1>

      {/* Account Balance Card */}
      <div className="bg-white rounded-lg shadow-md p-6 mb-8">
        <h2 className="text-lg font-medium text-gray-700 mb-2">Solde du compte</h2>
        <p className="text-3xl font-bold text-gray-900">{accountBalance.toLocaleString('fr-FR', { style: 'currency', currency: 'EUR' })}</p>
      </div>

      {/* Quick Actions */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">
        <Link
          to="/transfer"
          className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow flex items-center"
        >
          <ArrowUpRight className="h-8 w-8 text-blue-600 mr-4" />
          <div>
            <h3 className="font-medium text-gray-900">Effectuer un virement</h3>
            <p className="text-sm text-gray-500">Transférer de l'argent</p>
          </div>
        </Link>

        <Link
          to="/withdraw"
          className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow flex items-center"
        >
          <ArrowDownRight className="h-8 w-8 text-blue-600 mr-4" />
          <div>
            <h3 className="font-medium text-gray-900">Retrait</h3>
            <p className="text-sm text-gray-500">Retirer de l'argent</p>
          </div>
        </Link>

        <div className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow flex items-center">
          <Clock className="h-8 w-8 text-blue-600 mr-4" />
          <div>
            <h3 className="font-medium text-gray-900">Historique</h3>
            <p className="text-sm text-gray-500">Voir les transactions</p>
          </div>
        </div>
      </div>

      {/* Recent Transactions */}
      <div className="bg-white rounded-lg shadow-md">
        <div className="px-6 py-4 border-b border-gray-200">
          <h2 className="text-lg font-medium text-gray-900">Transactions récentes</h2>
        </div>
        <div className="divide-y divide-gray-200">
          {recentTransactions.map((transaction) => (
            <div key={transaction.id} className="px-6 py-4 flex items-center justify-between">
              <div className="flex items-center">
                <div className={`p-2 rounded-full ${
                  transaction.type === 'credit' ? 'bg-green-100' : 'bg-red-100'
                } mr-4`}>
                  {transaction.type === 'credit' ? (
                    <ArrowDownRight className={`h-5 w-5 ${
                      transaction.type === 'credit' ? 'text-green-600' : 'text-red-600'
                    }`} />
                  ) : (
                    <ArrowUpRight className={`h-5 w-5 ${
                      transaction.type === 'credit' ? 'text-green-600' : 'text-red-600'
                    }`} />
                  )}
                </div>
                <div>
                  <p className="font-medium text-gray-900">{transaction.description}</p>
                  <p className="text-sm text-gray-500">{transaction.date}</p>
                </div>
              </div>
              <span className={`font-medium ${
                transaction.type === 'credit' ? 'text-green-600' : 'text-red-600'
              }`}>
                {transaction.type === 'credit' ? '+' : '-'}
                {transaction.amount.toLocaleString('fr-FR', { style: 'currency', currency: 'EUR' })}
              </span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;