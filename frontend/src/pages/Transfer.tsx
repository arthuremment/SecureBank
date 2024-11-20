import React from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';

const transferSchema = z.object({
  recipientName: z.string().min(2, 'Le nom du bénéficiaire est requis'),
  recipientAccount: z.string().min(14, 'IBAN invalide').max(34, 'IBAN invalide'),
  amount: z.number().min(0.01, 'Le montant minimum est de 0.01€'),
  description: z.string().optional(),
});

type TransferForm = z.infer<typeof transferSchema>;

const Transfer = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<TransferForm>({
    resolver: zodResolver(transferSchema),
  });

  const onSubmit = (data: TransferForm) => {
    console.log(data);
    // TODO: Implement transfer logic
  };

  return (
    <div className="max-w-2xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <h1 className="text-2xl font-bold text-gray-900 mb-8">Effectuer un virement</h1>

      <div className="bg-white rounded-lg shadow-md p-6">
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
          <div>
            <label htmlFor="recipientName" className="block text-sm font-medium text-gray-700">
              Nom du bénéficiaire
            </label>
            <div className="mt-1">
              <input
                {...register('recipientName')}
                type="text"
                className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
              />
              {errors.recipientName && (
                <p className="mt-2 text-sm text-red-600">{errors.recipientName.message}</p>
              )}
            </div>
          </div>

          <div>
            <label htmlFor="recipientAccount" className="block text-sm font-medium text-gray-700">
              IBAN du bénéficiaire
            </label>
            <div className="mt-1">
              <input
                {...register('recipientAccount')}
                type="text"
                placeholder="FR76 XXXX XXXX XXXX XXXX XXXX XXX"
                className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
              />
              {errors.recipientAccount && (
                <p className="mt-2 text-sm text-red-600">{errors.recipientAccount.message}</p>
              )}
            </div>
          </div>

          <div>
            <label htmlFor="amount" className="block text-sm font-medium text-gray-700">
              Montant (€)
            </label>
            <div className="mt-1">
              <input
                {...register('amount', { valueAsNumber: true })}
                type="number"
                step="0.01"
                className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
              />
              {errors.amount && (
                <p className="mt-2 text-sm text-red-600">{errors.amount.message}</p>
              )}
            </div>
          </div>

          <div>
            <label htmlFor="description" className="block text-sm font-medium text-gray-700">
              Description (optionnel)
            </label>
            <div className="mt-1">
              <textarea
                {...register('description')}
                rows={3}
                className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
              />
              {errors.description && (
                <p className="mt-2 text-sm text-red-600">{errors.description.message}</p>
              )}
            </div>
          </div>

          <div className="flex items-center justify-between">
            <div className="text-sm">
              <p className="text-gray-500">
                Vérifiez bien les informations avant de valider le virement
              </p>
            </div>
          </div>

          <div>
            <button
              type="submit"
              className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
            >
              Effectuer le virement
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Transfer;