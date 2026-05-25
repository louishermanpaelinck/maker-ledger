'use client';

import { useState } from 'react';
import { Package, TrendingUp, DollarSign, AlertTriangle } from 'lucide-react';

export default function Home() {
  const [activeTab, setActiveTab] = useState<'dashboard' | 'materials' | 'projects'>('dashboard');

  return (
    <div className="min-h-screen bg-zinc-950">
      {/* Sidebar */}
      <div className="fixed left-0 top-0 h-full w-64 bg-zinc-900 border-r border-zinc-800 p-6">
        <div className="flex items-center gap-3 mb-12">
          <div className="w-9 h-9 bg-emerald-500 rounded-xl flex items-center justify-center">
            <Package className="w-5 h-5" />
          </div>
          <div>
            <h1 className="text-2xl font-bold tracking-tight">MakerLedger</h1>
            <p className="text-xs text-zinc-500">hobby profit tracker</p>
          </div>
        </div>

        <nav className="space-y-2">
          {[
            { id: 'dashboard', label: 'Dashboard', icon: TrendingUp },
            { id: 'materials', label: 'Materials', icon: Package },
            { id: 'projects', label: 'Projects', icon: DollarSign },
          ].map(({ id, label, icon: Icon }) => (
            <button
              key={id}
              onClick={() => setActiveTab(id as any)}
              className={`w-full flex items-center gap-3 px-4 py-3 rounded-xl transition-all ${
                activeTab === id 
                  ? 'bg-zinc-800 text-white' 
                  : 'hover:bg-zinc-900 text-zinc-400'
              }`}
            >
              <Icon className="w-5 h-5" />
              {label}
            </button>
          ))}
        </nav>
      </div>

      {/* Main Content */}
      <div className="ml-64 p-10">
        <div className="max-w-6xl mx-auto">
          <header className="mb-12">
            <h2 className="text-4xl font-semibold tracking-tight">
              {activeTab === 'dashboard' && 'Dashboard'}
              {activeTab === 'materials' && 'Materials Inventory'}
              {activeTab === 'projects' && 'Projects & Costs'}
            </h2>
            <p className="text-zinc-500 mt-2">Track smarter. Profit more.</p>
          </header>

          {activeTab === 'dashboard' && <Dashboard />}
          {activeTab === 'materials' && <Materials />}
          {activeTab === 'projects' && <Projects />}
        </div>
      </div>
    </div>
  );
}

// Placeholder Components
function Dashboard() {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
      <div className="bg-zinc-900 rounded-2xl p-8 border border-zinc-800">
        <div className="flex justify-between">
          <div>
            <p className="text-sm text-zinc-500">Total Spent</p>
            <p className="text-4xl font-semibold mt-3">€842</p>
          </div>
          <DollarSign className="w-8 h-8 text-zinc-400" />
        </div>
      </div>
      
      <div className="bg-zinc-900 rounded-2xl p-8 border border-zinc-800">
        <div className="flex justify-between">
          <div>
            <p className="text-sm text-zinc-500">Total Earned</p>
            <p className="text-4xl font-semibold mt-3 text-emerald-400">€1,340</p>
          </div>
          <TrendingUp className="w-8 h-8 text-emerald-400" />
        </div>
      </div>
      
      <div className="bg-zinc-900 rounded-2xl p-8 border border-zinc-800 col-span-2">
        <h3 className="font-medium mb-6">Quick Stats</h3>
        <div className="grid grid-cols-3 gap-6">
          <div>
            <p className="text-3xl font-semibold">24</p>
            <p className="text-sm text-zinc-500">Projects Made</p>
          </div>
          <div>
            <p className="text-3xl font-semibold text-emerald-400">€498</p>
            <p className="text-sm text-zinc-500">Profit</p>
          </div>
          <div>
            <div className="flex items-center gap-2 text-amber-400">
              <AlertTriangle className="w-5 h-5" />
              <p className="font-medium">3 low stock</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

function Materials() {
  return (
    <div className="bg-zinc-900 rounded-3xl p-8">
      <div className="flex justify-between items-center mb-8">
        <h3 className="text-xl font-semibold">Your Inventory</h3>
        <button className="bg-white text-black px-6 py-3 rounded-2xl font-medium flex items-center gap-2 hover:bg-zinc-200 transition">
          + Add Material
        </button>
      </div>
      <p className="text-zinc-400">Full materials table coming soon...</p>
    </div>
  );
}

function Projects() {
  return (
    <div>
      <div className="bg-zinc-900 rounded-3xl p-8 mb-8">
        <h3 className="text-xl font-semibold mb-6">New Project Calculator</h3>
        <p className="text-zinc-400">Universal Price Engine demo coming soon...</p>
      </div>
    </div>
  );
}