using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WCFService;

/// <summary>
/// Summary description for Data
/// </summary>
public class Data
{
    public static List<String> ListBooks()
    {
        using (Model m = new Model())
        {
            return m.Books.Select<Book, String>(c => c.id).ToList<String>();
        }
    }

    public static List<String> ListBookChoice()
    {
        using (Model m = new Model())
        {
            var a = m.Books.Select<Book, String>(c => c.cg).Distinct();
            return a.ToList<String>();
        }
    }

    public static Book GetBook(string id)
    {
        using (Model m = new Model())
        {
            return m.Books.Where
                    (p => p.id == id).ToList<Book>()[0];
        }
    }


    public static List<Book> GetBookByCg(string cg)
    {
        using (Model m = new Model())
        {
            return m.Books.Where
                    (p => p.cg == cg).ToList<Book>();
        }
    }


    public static void InsertBook(Book c)
    {
        using (Model m = new Model())
        {
            m.Books.Add(c);
            m.SaveChanges();
        }
    }
    public static void UpdateBook(Book c)
    {
        using (Model m = new Model())
        {
            m.Entry(c).State = System.Data.Entity.EntityState.Modified;
            
            m.SaveChanges();
        }
    }
    public static void DeleteBook(Book c)
    {
        using (Model m = new Model())
        {
            m.Entry(c).State = System.Data.Entity.EntityState.Deleted;
            m.SaveChanges();
        }
    }
}