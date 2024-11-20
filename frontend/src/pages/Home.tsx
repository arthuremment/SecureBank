import React from 'react';
import { Link } from 'react-router-dom';
import { Shield, Banknote, Clock, CreditCard } from 'lucide-react';

const Home = () => {
  return (
    <div className="flex flex-col min-h-screen">
      {/* Hero Section */}
      <section className="relative bg-gradient-to-r from-blue-600 to-blue-800 py-32">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h1 className="text-4xl sm:text-5xl font-bold text-white mb-6">
            Bienvenue sur SecureBank
          </h1>
          <p className="text-xl text-blue-100 mb-8">
            Votre banque en ligne sécurisée pour gérer votre argent en toute simplicité
          </p>
          <div className="flex justify-center gap-4">
            <Link
              to="/register"
              className="bg-white text-blue-600 px-6 py-3 rounded-lg font-semibold hover:bg-blue-50 transition-colors"
            >
              Ouvrir un compte
            </Link>
            <Link
              to="/login"
              className="border-2 border-white text-white px-6 py-3 rounded-lg font-semibold hover:bg-white/10 transition-colors"
            >
              Se connecter
            </Link>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-24 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h2 className="text-3xl font-bold text-center text-gray-900 mb-16">
            Nos Services
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            <FeatureCard
              icon={<Shield className="h-8 w-8 text-blue-600" />}
              title="Sécurité Maximale"
              description="Protection avancée de vos données et transactions"
            />
            <FeatureCard
              icon={<Banknote className="h-8 w-8 text-blue-600" />}
              title="Virements Instantanés"
              description="Transferts d'argent rapides et sécurisés"
            />
            <FeatureCard
              icon={<Clock className="h-8 w-8 text-blue-600" />}
              title="24/7 Disponible"
              description="Accédez à vos comptes à tout moment"
            />
            <FeatureCard
              icon={<CreditCard className="h-8 w-8 text-blue-600" />}
              title="Gestion Simplifiée"
              description="Interface intuitive pour gérer vos finances"
            />
          </div>
        </div>
      </section>
    </div>
  );
};

const FeatureCard = ({ icon, title, description }: { icon: React.ReactNode; title: string; description: string }) => (
  <div className="p-6 bg-gray-50 rounded-xl text-center hover:shadow-lg transition-shadow">
    <div className="flex justify-center mb-4">{icon}</div>
    <h3 className="text-xl font-semibold text-gray-900 mb-2">{title}</h3>
    <p className="text-gray-600">{description}</p>
  </div>
);

export default Home;