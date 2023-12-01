package Phonebook;
import java.util.Date;
//BST with methods to insert, remove, update, and search for nodes according to various standards
public class BST<K extends Comparable<K>,T> {
	//THE NODE CLASS
	class BSTNode <K extends Comparable<K>,T> {
		public K key;  
		public T data;
		public BSTNode<K,T> left, right;

		/** Creates a new instance of BTNode */
		public BSTNode() {
			left = right = null;
		}

		public BSTNode(K key, T data) {
			this.key = key  ;  
			this.data = data;
			left = right = null;
		}

		public BSTNode(K key, T data, BSTNode<K,T> l, BSTNode<K,T> r){
			this.key = key  ;  
			this.data = data;
			left = l;
			right = r;
		}

		@Override
		public String toString() {
			return " ["+ "key=" + key + ", data=" + data + "] ";
		}

	}//end of node class
	
	BSTNode<K,T> root, current;

	/** Creates a new instance of BST */
	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve () {
		return current.data;
	}

	public boolean findkey(K tkey) {
		BSTNode<K,T>  p = root;
		BSTNode<K,T>  q = root;
		
		if(empty() || tkey==null)
			return false;

		while(p != null) {
			q = p;
			if(p.key.compareTo(tkey) == 0) {
				current = p;
				return true;
			}
			else if(tkey.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		current = q;
		return false;
	}
	
	public boolean insert(K k, T val) {
		BSTNode<K,T>  p;
		BSTNode<K,T>  q = current;

		if(findkey(k)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}

		p = new BSTNode<K,T> (k, val);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k.compareTo(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	public boolean remove_key (K tkey){
		boolean removed =false;
		BSTNode<K,T>  p;
		p = remove_aux(tkey, root, removed);
		current = root = p;
		return removed;
	}

	private BSTNode<K,T>  remove_aux(K key, BSTNode<K,T>  p, boolean flag) {
		BSTNode<K,T>  q, child = null;
		if(p == null)
			return null;
		if(key.compareTo(p.key) <0)
			p.left = remove_aux(key, p.left, flag); //go left
			else if(key.compareTo(p.key) >0)
				p.right = remove_aux(key, p.right, flag); //go right
			else {
				flag = true;
				if (p.left != null && p.right != null){ //two children
					q = find_min(p.right);
					p.key = q.key;
					p.data = q.data;
					p.right = remove_aux(q.key, p.right, flag);
				}
				else {
					if (p.right == null) //one child
						child = p.left;
					else if (p.left == null) //one child
						child = p.right;
					return child;
				}
			}
		return p;
	}

	private BSTNode<K,T>  find_min(BSTNode<K,T>  p)
	{
		if(p == null)
			return null;

		while(p.left != null){
			p = p.left;
		}

		return p;
	}

	public boolean update(K key, T data)
	{
		K key1 = key; 
		remove_key(key1);
		return insert(key1, data);
	}
	//Method removeKey: iterative  
	public boolean removeKey(K k) {
		// Search 
		K k1 = k;      
		BSTNode<K,T>  p = root;      
		BSTNode<K,T>  q = null;    // Parent of p

		while (p != null) 
		{
			if (k1.compareTo(p.key) <0) 
			{
				q =p;
				p = p.left;
			}
			else if (k1.compareTo(p.key) >0) 
			{
				q = p;
				p = p.right;
			} 
			else { 
				// Found the key            
				// Check the three cases
				if ((p.left != null) && (p.right != null)) 
				{ 
					// Case 3: two children               
					// Search for the min in the right subtree
					BSTNode<K,T>  min = p.right;
					q = p;
					while (min.left != null) 
					{
						q = min;
						min = min.left;
					}
					p.key = min.key;               
					p.data = min.data;
					k1 = min.key;
					p = min;
					// Now fall back to either case 1 or 2
				}
				// The subtree rooted at p will change here            
				if (p.left != null) 
				{ 
					// One child
					p = p.left;
				} 
				else 
				{ 
					// One or no children
					p = p.right;
				}

				if (q == null) 
				{ 
					// No parent for p, root must change
					root = p;
				} 
				else 
				{
					if (k1.compareTo(q.key) <0) 
					{
						q.left = p;
					} 
					else 
					{
						q.right = p;
					}
				}
				current = root;
				return true;
			}
		}
		return false; // Not found
	}
	//
	/* 
    In order traversal
	 */
	@Override
	public String toString() 
	{
		String str = "";
		if ( root == null)
			return str;
		str = RecursiveInOrderTraversal ( root , str );
		return str;
	}

	private String RecursiveInOrderTraversal (BSTNode <K, T> p ,String str)
	{
		if (p == null)
			return "";
		else
		{
			str = RecursiveInOrderTraversal(p.left , str);
			str += p.data.toString() + "    ";
			str += RecursiveInOrderTraversal(p.right, str);
		}
		return str;
	}

	//
	// Search contact phone in the BST O(n)
	//
	public boolean SearchPhone(String phone)
	{
		return SearchPhoneRecursive(root, phone);
	}
	private boolean SearchPhoneRecursive (BSTNode <K, T> p, String phone)
	{
		if (p == null)
			return false;
		else    if (((Contact)p.data).compareToPhone(phone) == 0)
		{
			current = p;

			return true;
		}

		return (SearchPhoneRecursive(p.left , phone) || SearchPhoneRecursive(p.right, phone));
	}

	//
	// Search contact email in the BST O(n)
	//
	public boolean SearchEmail(String email)
	{
		return SearchEmailRecursive (root, email);
	}
	private boolean SearchEmailRecursive(BSTNode <K, T> p, String email)
	{
		if (p == null)
			return false;

		else    if (((Contact)p.data).compareToEmail(email) == 0)
		{
			current = p;

			return true;
		}

		return (SearchEmailRecursive(p.left , email) || SearchEmailRecursive(p.right, email));
	}

	//
	// Search contact address in the BST O(n)
	//
	public boolean SearchAddress(String address)
	{
		return SearchAddressRecursive (root, address);
	}
	private boolean SearchAddressRecursive (BSTNode <K, T> p, String address)
	{
		if (p == null)
			return false;
		else    if (((Contact)p.data).compareToAddress(address) == 0)
		{
			current = p;
			return true;
		}

		return (SearchAddressRecursive(p.left , address) || SearchAddressRecursive(p.right, address));
	}

	//
	// Search contact birthday in the BST O(n)
	//
	public boolean SearchBirthday(Date birthday)
	{
		return SearchBirthdayRecursive(root, birthday);
	}
	private boolean SearchBirthdayRecursive (BSTNode <K, T> p, Date birthday)
	{
		if (p == null)
			return false ;
		else    if (((Contact)p.data).compareToBirthday(birthday) == 0)
		{
			current = p;
			return true;
		}
		

		return (SearchBirthdayRecursive(p.left , birthday) || SearchBirthdayRecursive(p.right, birthday));
	}


	// Search contact birthday in the BST O(n)
	
	public boolean SearchSameFirstName(String name)
	{
		return SearchSameFirstNameRecursive (root, name.trim());
	}
	private boolean SearchSameFirstNameRecursive (BSTNode <K, T> p, String name)
	{
		if (p == null)
			return false;
		else    if (((Contact)p.data).compareFirstName(name) == 0)
		{
			current = p;
			return true;
		}

		return (SearchSameFirstNameRecursive(p.left , name) || SearchSameFirstNameRecursive(p.right, name));
	}}
