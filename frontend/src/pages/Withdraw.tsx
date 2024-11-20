import React from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';

const withdrawSchema = z.object({
  amount: z.number()
    .min(20, 'Le montant minimum est de 20€')
    .max(1000, 'Le montant maximum est de 1000€'),
  description: z.string().optional(),
});

type WithdrawForm = z.infer<typeof withdrawSchema>;

const Withdraw = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<WithdrawForm>({
    resolver: zodResolver(withdrawSchema),
  });

  const onSubmit = (data: WithdrawForm) => {
    console.log(data);
    // TODO: Implement withdraw logic
  };

  return (
    <div className="max-w-2xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <h1 className="text-2xl font-bold text-gray-900 mb-8">Retrait d'argent</h1>

      <div className="bg-white rounded-lg shadow-md p-6">
        <div className="mb-6">
          <div className="bg-yellow-50 border-l-4 border-yellow-400 p-4">
            <div className="flex">
              <div className="flex-shrink-0">
                <svg className="h-5 w-5 text-yellow-400" viewBox="0 0 20 20" fill="currentColor">
                  <path fillRule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clipRule="evenodd" />
                </svg>
              </div>
              <div className="ml-3">
                <p className="text-sm text-yellow-700">
                  Les retraits sont limités à 1000€ par jour pour votre sécurité.
                </p>
              </div>
            </div>
          </div>
        </div>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
          <div>
            <label htmlFor="amount" className="block text-sm font-medium text-gray-700">
              Montant (€)
            </label>
            <div className="mt-1">
              <input
                {...register('amount', { valueAsNumber: true })}
                type="number"
                step="10"
                className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
              />
              {errors.amount && (
                <p className="mt-2 text-sm text-red-600">{errors.amount.message}</p>
              )}
            </div>
          </div>

          <div>
            <label htmlFor="description" className="block text-sm font-medium text-gray-700">
              Motif (optionnel)
            </label>
            <div className="mt-1">
              <input
                {...register('description')}
                type="text"
                className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
          </div>

          <div className="bg-gray-50 p-4 rounded-md">
            <h4 className="text-sm font-medium text-gray-900 mb-2">Billets disponibles :</h4>
            <ul className="text-sm text-gray-600 space-y-1">
              <li>• Billets de 10€</li>
              <li>• Billets de 20€</li>
              <li>• Billets de 50€</li>
            </ul>
          </div>

          <div>
            <button
              type="submit"
              className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
            >
              Valider le retrait
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Withdraw;