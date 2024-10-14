/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/templates/**/*.{html,js}",
    'node_modules/preline/dist/*.js'
  ],
  theme: {
    extend: {
      colors: {
        'red-prim': '#C1111E',
        'red-sec': '#F03D3E',
        'background': '#F8F9FA',
        'dark-1': '#212429',
        'dark-2': '#7A7A7A',
        'dark-3': '#D9D9D9',
        'dark-4': '#495057',
        'light-1': '#F9F9F9'
      },
      height: {
        '112': '28rem',
        '128': '32rem',
      },
      boxShadow: {
        'card': '0px 2px 11.1px 0px rgba(0, 0, 0, 0.10)',
        'card-hover': '0px 4px 8px rgba(0, 0, 0, 0.1)',
      },
      fontFamily: {
        archivo: ['Archivo Black', 'sans-serif'],
        roboto: ['Roboto', 'sans-serif'],
        
      },
    },
  },
  plugins: [
    require('@tailwindcss/typography'),
    require('@tailwindcss/line-clamp'),
    require('flowbite/plugin'),
    require('preline/plugin'),
  ],
}

