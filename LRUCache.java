import java.util.HashMap;

/* LRU cache implementation  */
/* Least Recently Used Cache */

class Entry
{
	int value;
	int key;
	Entry left;
	Entry right;
}



public class LRUCache
{
	public static HashMap<Integer, Entry> hm = new HashMap<Integer, Entry>();
	public static Entry start;
	public static Entry end;
	//Testing the LRU size.
	public static int LRU_SIZE = 4;
	
	public LRUCache()
	{
		hm = new HashMap<Integer, Entry>();
	}
	
	public static int getEntry(int key)
	{
		if(hm.containsKey(key))
		{
			Entry entry = hm.get(key);
			removeNode(entry);
			addAtTop(entry);
			return entry.value;
		}
		return -1;
	}
	
	
	public static void putEntry(int key, int value)
	{
		if(hm.containsKey(key))
		{
			Entry entry = hm.get(key);
			entry.value = value;
			removeNode(entry);
			addAtTop(entry);
		}
		else
		{
			Entry newnode = new Entry();
			newnode.left= null;
			newnode.right = null;
			newnode.value=value;
			newnode.key= key;
			if(hm.size() >LRU_SIZE)
			{
				hm.remove(end.key);
				removeNode(end);
				addAtTop(newnode);
			}
			else
			{
				addAtTop(newnode);
			}
			hm.put(key,newnode);
		}
	}
	
	public static void addAtTop(Entry node)
	{
		node.right = start;
		node.left = null;
		if(start != null)
		{
			start.left = node;
		}
		start = node;
		if(end == null)
		{
			end = start;
		}
	}
	
	
	public static void removeNode(Entry node)
	{
		if(node.left != null)
		{
			node.left.right = node.right;
		}
		else
		{
			start = node.right;
		}
		
		if(node.right != null)
		{
			node.right.left = node.left;
		}
		else
		{
			end = node.left;
		}
	}
	public static void main(String[] args)
	{
		LRUCache cache = new LRUCache();
		cache.putEntry(1,1);
		cache.putEntry(10,15);
		cache.putEntry(15,10);
		cache.putEntry(10, 16);
		cache.putEntry(12, 15);
		cache.putEntry(18, 10);
		cache.putEntry(13, 16);
		
		System.out.println(cache.getEntry(1));
		System.out.println(cache.getEntry(10));
		System.out.println(cache.getEntry(15));
	}
}