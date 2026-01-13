function createShoppingCart() {
  let items = [];
  let discountPercent = 0;

  return {
    
    addItem(product) {
      const existingItem = items.find(item => item.id === product.id);

      if (existingItem) {
        existingItem.quantity += product.quantity;
      } else {
        items.push({ ...product });
      }
    },

    
    removeItem(id) {
      items = items.filter(item => item.id !== id);
    },

    
    updateQuantity(id, quantity) {
      const item = items.find(item => item.id === id);
      if (item && quantity > 0) {
        item.quantity = quantity;
      }
    },

    
    getItems() {
      return items.map(item => ({ ...item }));
    },

    
    getTotal() {
      const total = items.reduce((sum, item) => {
        return sum + item.price * item.quantity;
      }, 0);

      const discountAmount = (total * discountPercent) / 100;
      return +(total - discountAmount).toFixed(2);
    },

    
    getItemCount() {
      return items.reduce((count, item) => count + item.quantity, 0);
    },

   
    isEmpty() {
      return items.length === 0;
    },

    
    applyDiscount(code, percent) {
      if (typeof percent === 'number' && percent > 0) {
        discountPercent = percent;
      }
    },

   
    clear() {
      items = [];
      discountPercent = 0;
    }
  };
}

const cart = createShoppingCart();

cart.addItem({ id: 1, name: 'Laptop', price: 999, quantity: 1 });
cart.addItem({ id: 2, name: 'Mouse', price: 29, quantity: 2 });
cart.addItem({ id: 1, name: 'Laptop', price: 999, quantity: 1 });


