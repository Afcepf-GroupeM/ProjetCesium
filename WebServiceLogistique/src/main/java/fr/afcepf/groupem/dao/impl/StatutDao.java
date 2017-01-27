package fr.afcepf.groupem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.dao.api.IStatutDao;
import fr.afcepf.groupem.entities.Livraison;
import fr.afcepf.groupem.entities.Statut;
import fr.afcepf.groupem.entities.StatutLine;

@Transactional
@Component
public class StatutDao implements IStatutDao {
	
	@PersistenceContext(unitName="WebServiceLogistique")
	private EntityManager entityManager;

	@Override
	public Statut getStatutByLivraisonId(int idLivraison) {
		return entityManager.createQuery("SELECT s FROM Statut s INNER JOIN s.Livraison l WHERE l.id = :idLivraison", Statut.class).setParameter("idLivraison", idLivraison).getSingleResult();
	}

	@Override
	public List<StatutLine> getStatutLinesByStatutId(int idStatut) {
		return entityManager.createQuery("SELECT sl FROM StatutLine sl INNER JOIN sl.statut s WHERE s.id = :idStatut", StatutLine.class).setParameter("idStatut", idStatut).getResultList();
	}

	@Override
	public Statut createStatut(Livraison livraison) {
		Statut statut = new Statut();
		statut.setLivraison(livraison);
		entityManager.persist(statut);
		return statut;
	}

	@Override
	public StatutLine createStatutLine(StatutLine statutLine) {
		entityManager.persist(statutLine);
		return statutLine;
	}

	@Override
	public Statut updateStatut(Statut statut) {
		entityManager.merge(statut);
		return statut;
	}

}
