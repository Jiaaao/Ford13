import java.util.*;

class NFlow
{


  static int V;
  static int M;
  static int N;
  static boolean matrix[][];

  void amg(int V)
  {
    M = V;
    N = V;
    matrix = new boolean[M][N];
  }

	boolean bpm(boolean matrix[][], int u,
				boolean seen[], int matchR[])
	{
		for (int v = 0; v < N; v++)
		{

			if (matrix[u][v] && !seen[v])
			{


				seen[v] = true;

				if (matchR[v] < 0 || bpm(matrix, matchR[v],
										seen, matchR))
				{
					matchR[v] = u;
					return true;
				}
			}
		}
		return false;
	}

	int maxBPM(boolean matrix[][])
	{
		int matchR[] = new int[N];

		for(int i = 0; i < N; ++i)
			matchR[i] = -1;

		int result = 0;
		for (int u = 0; u < M; u++)
		{
			boolean seen[] =new boolean[N] ;
			for(int i = 0; i < N; ++i)
				seen[i] = false;

			if (bpm(matrix, u, seen, matchR))
				result++;
		}
		return result;
	}


  static void addEdge(int m, int n)
  {
    matrix[m][n] = true;
  }


  void printGraph(){
    for(int i = 0; i<M; i++){
      for (int j = 0; j < N ; j++){
        System.out.print(matrix[i][j]+ " ");
      }
      System.out.println();
    }
  }

	public static void main (String[] args)
	{

    NFlow nflow = new NFlow();
    Scanner input = new Scanner(System.in);
    System.out.println("Note: Left and Right Subset will have the same size");
    System.out.println("Enter number of vertices: ");
    int V = input.nextInt();
    nflow.amg(V);
    System.out.println("Enter number of Edges: ");
    int E = input.nextInt();

    for(int i = 0; i < E; i++)
    {
      System.out.println("Enter matching vertices ");
      int m = input.nextInt();
      int n = input.nextInt();

      nflow.addEdge(m,n);
    }

    //nflow.printGraph();


		System.out.println( "Maximum number of matches: "+nflow.maxBPM(matrix));
	}
}
