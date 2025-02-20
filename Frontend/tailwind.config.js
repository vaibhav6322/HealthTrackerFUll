export default {
  important: true, // Forces Tailwind to take priority over Bootstrap
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}", 
    "./public/**/*.html", 
  ],
  theme: {
    extend: {},
  },
  plugins: [],
};
